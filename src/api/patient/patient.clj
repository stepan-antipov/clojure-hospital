(ns api.patient.patient
  (:require
   [api.patient.validation :refer [is-add-handler-valid?]]))


(defn add [request]
  (let [body (:body request)
        {:keys [first-name last-name gender birth city street house mid]} body]
    (if (is-add-handler-valid? body)
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body {:is-valid? true
              :first-name first-name
              :last-name last-name
              :gender gender
              :birth birth
              :city city
              :street street
              :house house
              :mid mid}}
      {:status 400
       :headers {"Content-Type" "application/json"}
       :body {:is-valid? false
              :first-name first-name
              :last-name last-name
              :gender gender
              :birth birth
              :city city
              :street street
              :house house
              :mid mid}})))

(defn delete [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "delete"})

(defn edit [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "edit"})

(defn get-by-id [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "get"})

(defn search [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "search"})

(defn get-all [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "get-all"})
