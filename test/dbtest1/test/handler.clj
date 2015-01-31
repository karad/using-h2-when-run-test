(ns dbtest1.test.handler
  (:use clojure.test
        ring.mock.request
        dbtest1.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))

(use-fixtures :once dbtest1.common.init/once-fixture)
(use-fixtures :each dbtest1.common.init/each-fixture)

