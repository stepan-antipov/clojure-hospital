(ns clojure_hospital.core
  (:require
   ;; http-server
   [ring.adapter.jetty :refer [run-jetty]]
   ;; fast server reload
   [ring.middleware.reload :refer [wrap-reload]]))


(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello, baby!!!"})


(def wrapped-handler (wrap-reload #'handler))


;; run server on 3002 port, join? false allows to execute other programms when server works

(defn -main []
  (run-jetty wrapped-handler {:port 3002
                              :join? false}))

