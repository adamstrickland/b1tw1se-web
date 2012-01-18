(ns b1tw1se.server
  (:require [noir.server :as server]))

; (server/load-views "src/b1tw1se/models/" "src/b1tw1se/views/" "src/b1tw1se/config/")
(def srcdirs [
	"models" 
	"views" 
	"config" 
	"lib"])
(apply server/load-views (map #(str "src/b1tw1se/" % "/") srcdirs))

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'b1tw1se})))

