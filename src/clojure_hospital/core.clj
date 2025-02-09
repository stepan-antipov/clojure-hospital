(ns clojure-hospital.core
  (:gen-class)
  (:require
   ;; http-server
   [ring.adapter.jetty :refer [run-jetty]]
   ;; fast server reload
   [ring.middleware.reload :refer [wrap-reload]]
   ;; routing
   [reitit.ring :as ring]
   [db.init-tables :as init-tables]))

;; clj -M -m clojure-hospital.core

;; -M основной режим
;; -m пространство имен, в котором находится функция -main



(defn handler [request]
  (println (:path-params request))
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello, baby!!!"})

;; wrapper for fast code reload. Only for dev!

(def wrapped-handler (wrap-reload #'handler))


(def app
  (ring/ring-handler
    (ring/router
      [["/" wrapped-handler]
       ["/hello/:id" wrapped-handler]])))


;; run server on 3002 port, join? false allows to execute other programms when server works

(defn -main [& args]
  (if (= (first args) "init-tables")
    (init-tables/-main)
    (run-jetty app {:port 3002
                    :join? false})))

;; sql-postgres for connecting to database from emacs
