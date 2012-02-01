(defproject b1tw1se "0.1.0-SNAPSHOT"
    :description "b1tw1se : where the w1se ones are"
    :dependencies [[org.clojure/clojure "1.3.0"]
       [noir "1.2.1"]
       [congomongo "0.1.5-SNAPSHOT"]
       [clj-oauth/clj-oauth "1.2.10-SNAPSHOT"]
       [clj-time "0.3.4"]]
	:dev-dependencies [[com.stuartsierra/lazytest "1.2.3"]
       [lein-autotest "1.1.0"]]
	:repositories {"stuartsierra-releases" "http://stuartsierra.com/maven2"}
       :main b1tw1se.server)
