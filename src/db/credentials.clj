(ns db.credentials
  (:require [config :refer [dbname dbtype host user password]]))

;; config for protgresql database connection

(def db {:dbtype dbtype
         :dbname dbname
         :host host
         :user user
         :password password})
