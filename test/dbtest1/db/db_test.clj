(ns dbtest1.db.db-test
  (:use clojure.test
        ring.mock.request)
  (:require [dbtest1.db.core :as core]))

(declare user1)

(deftest db-test
  (testing
    "db-test-1"
    (is (= 1 1))))

(deftest insert-test
  (testing "insert-test-1"
    (let [result (core/create-user user1)
          user (core/get-user 1)]
      (prn result)
      (is (= "XXXX" (:pass user)))
      (is (= "hoge" (:first_name user)))
      (is (= "foo" (:last_name user)))
      )))

(use-fixtures :once dbtest1.common.init/once-fixture)
(use-fixtures :each dbtest1.common.init/each-fixture)

(def user1 {:id         1
            :first_name "hoge"
            :last_name  "foo"
            :email      ""
            :admin      false
            :is_active  false
            :pass       "XXXX"
            })
