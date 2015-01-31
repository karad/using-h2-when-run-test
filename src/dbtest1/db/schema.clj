(ns dbtest1.db.schema
  (:require [korma.db :as db]
            [clojure.java.io :refer [file]]
            [noir.io :as io]
            [environ.core :as env]))

(def db-spec-mysql
  (db/mysql {:subprotocol "mysql"
             :subname     "//localhost:3306/dbtest1"
             :user        "root"
             :password    "root"
             :delimiters "`"}))

(def db-store (str (.getName (file ".")) "/site.db"))

(def db-spec-h2
  {:classname "org.h2.Driver"
   :subprotocol "h2"
   :subname db-store
   :user "sa"
   :password ""
   :make-pool? true
   :delimiters "`"
   :naming {:keys clojure.string/lower-case
            :fields clojure.string/upper-case}})

; (def db-spec db-spec-mysql)
; (def db-spec db-spec-h2)

(def db-spec (cond
               (env/env :test) db-spec-h2
               (env/env :dev) db-spec-mysql
               :else db-spec-mysql))

