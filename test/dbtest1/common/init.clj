(ns dbtest1.common.init
  (:use clojure.test
        ring.mock.request)
  (:require [clojure.java.jdbc :as sql]
            [dbtest1.db.schema :as schema]
            [environ.core :as env]))

(defn create-users-table
  "create user table"
  []
  (sql/db-do-commands
    schema/db-spec-h2
    (sql/create-table-ddl
      :users
      [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
      [:first_name "varchar(30)"]
      [:last_name "varchar(30)"]
      [:email "varchar(30)"]
      [:admin "tinyint(1)"]
      [:last_login :timestamp]
      [:is_active "tinyint(1)"]
      [:pass "varchar(100)"]
      ))
  ;(sql/db-do-prepared db-spec
  ;                    "CREATE INDEX timestamp_index ON users (timestamp)")
  )

(defn drop-table
  "drop table util"
  [entity]
  (try
    (do
      (sql/db-do-commands schema/db-spec-h2
                          (sql/drop-table-ddl entity))
      true)
    (catch Throwable ex
      false)))

(defn create-tables
  "create table group"
  []
  (create-users-table))

(defn drop-tables
  "drop table group"
  []
  (drop-table :users))

(defn one-time-setup
  "init once"
  []
  (do
    (prn "env :dev" (env/env :dev))
    (prn "env :production" (env/env :production))
    (prn "env :test" (env/env :test))
    (drop-tables)
    (create-tables)
    (prn "Test start")))

(defn one-time-teardown
  "shutdown last"
  []
  (do
    (prn "Test End")))

(defn once-fixture
  "init db"
  [f]
  (one-time-setup)
  (f)
  (one-time-teardown))

(defn setup
  "every test before"
  []
  (prn "setup..."))

(defn teardown
  "every test after"
  []
  (prn "teardown..."))

(defn each-fixture
  "every test"
  [f]
  (setup)
  (prn (type f))
  (f)
  (teardown))


(use-fixtures :once once-fixture)
(use-fixtures :each each-fixture)
