(defproject
  dbtest1
  "0.1.0-SNAPSHOT"
  :description
  "FIXME: write description"
  :url
  "http://example.com/FIXME"
  :dependencies
  [[prone "0.8.0"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [selmer "0.7.7"]
   [com.taoensso/tower "3.0.2"]
   [markdown-clj "0.9.58" :exclusions [com.keminglabs/cljx]]
   [mysql/mysql-connector-java "5.1.6"]
   [im.chit/cronj "1.4.3"]
   [com.taoensso/timbre "3.3.1"]
   [com.h2database/h2 "1.4.182"]
   [noir-exception "0.2.3"]
   [korma "0.4.0"]
   [lib-noir "0.9.5"]
   [org.clojure/clojure "1.6.0"]
   [environ "1.0.0"]
   [ring-server "0.3.1"]
   [ragtime "0.3.6"]]
  :repl-options
  {:init-ns dbtest1.repl}
  :jvm-opts
  ["-server"]
  :plugins
  [[lein-ring "0.9.0"]
   [lein-environ "1.0.0"]
   [lein-ancient "0.5.5"]
   [ragtime/ragtime.lein "0.3.6"]
   [lein-idea "1.0.1"]]
  :ring
  {:handler      dbtest1.handler/app,
   :init         dbtest1.handler/init,
   :destroy      dbtest1.handler/destroy,
   :uberwar-name "dbtest1.war"}
  :profiles
  {:uberjar {:omit-source true, :env {:production true}, :aot :all},
   :production
            {:ring
             {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
            {:dependencies
             [[ring-mock "0.1.5"]
              [ring/ring-devel "1.3.2"]
              [pjstadig/humane-test-output "0.6.0"]],
             :injections
                  [(require 'pjstadig.humane-test-output)
                   (pjstadig.humane-test-output/activate!)],
             :env {:dev true}}
   :test {:env {:test true}}}
  :ragtime
  {:migrations ragtime.sql.files/migrations,
   :database
               "jdbc:mysql://localhost:3306/dbtest1?user=db_user_name_here&password=db_user_password_here"}
  :uberjar-name
  "dbtest1.jar"
  :min-lein-version "2.0.0")