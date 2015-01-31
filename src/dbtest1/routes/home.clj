(ns dbtest1.routes.home
  (:require [compojure.core :refer :all]
            [dbtest1.layout :as layout]
            [dbtest1.util :as util]
            [dbtest1.db.core :as core]
            [dbtest1.db.schema :as schema]))

(def user1 {:id 1
            :first_name "hoge"
            :last_name  "foo"
            :email      ""
            :admin      false
            :is_active  false
            :pass       "XXXX"
            })

(defn home-page []
  (let [sql
        {}
        ;(core/create-user user1)
        ]
    (layout/render
      "home.html" {:content sql})))

(defn about-page []
  ;(schema/create-tables)
  (prn (core/get-user 1))
  (layout/render "about.html"))

(defroutes home-routes
           (GET "/" [] (home-page))
           (GET "/about" [] (about-page)))

