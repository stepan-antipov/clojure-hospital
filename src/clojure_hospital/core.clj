(ns clojure-hospital.core
  (:gen-class)
  (:require
   ;; http-server
   [ring.adapter.jetty :refer [run-jetty]]
   ;; fast server reload
   [ring.middleware.reload :refer [wrap-reload]]
   ;; json serialization
   [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
   ;; routing
   [reitit.ring :as ring]
   [db.init-tables :as init-tables]
   [api.patient.patient :as patient]))

;; clj -M -m clojure-hospital.core

;; -M основной режим
;; -m пространство имен, в котором находится функция -main


;; wrapper for fast code reload. Only for dev!


(def app
  (ring/ring-handler
    (ring/router
      [["/api"
        ["/patient"
         ["/add" {:post patient/add}]
         ["/delete" {:delete patient/delete}]
         ["/edit" {:post patient/edit}]
         ["/get" {:get patient/get-by-id}]
         ["/search" {:get patient/search}]
         ["/get-all" {:get patient/get-all}]]]])
    (ring/create-default-handler
      {:not-found (constantly {:status 404 :body "Oops... Not found"})})))


(def wrapped-app (->
                   #'app
                   ;; JSON -> clj.hash-map
                   (wrap-json-body {:keywords? true})
                   
                   ;; clj.hash-map -> JSON
                   wrap-json-response wrap-reload)) ;; TODO: fix before dev

;; run server on 3000 port, join? false allows to execute other programms when server works

(defn -main [& args]
  (println "Light weight, baby!!")
  (if (= (first args) "init-tables")
    (init-tables/-main)
    (run-jetty wrapped-app {:port 3000
                            :join? false})))
 

;; sql-postgres for connecting to database from emacs

