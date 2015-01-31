(ns dbtest1.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [dbtest1.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity users
           (database db))

(defn create-user [user]
  (-> (insert* users)
      (values user)
      ;(as-sql)
      (insert)
      ))

(defn update-user [id first-name last-name email]
  (update users
          (set-fields {:first_name first-name
                       :last_name  last-name
                       :email      email})
          (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))
