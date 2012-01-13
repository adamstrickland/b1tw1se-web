(ns b1tw1se.server
  (:require [noir.server :as server]))

(server/load-views "src/b1tw1se_web/views/")
(server/load-views "src/b1tw1se_web/models/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'b1tw1se})))

