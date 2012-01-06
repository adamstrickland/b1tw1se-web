(ns b1tw1se-web.views.welcome_mongo
  (:require [b1tw1se-web.views.common :as common])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

; (defpage "/welcome-mongo" []
;   (maybe-init)
;   (let [counter 
; 	(fetch-and-modify 
; 	 :firstcollection ;; In the collection named 'firstcollection',
; 	 {:_id "counter"} ;; find the counter record.
; 	 {:$inc {:value 1} } ;; Increment it.
; 	 :return-new true :upsert? true)] ;; Insert if not there.
;     (common/layout
;      [:p (str "Welcome to noir-heroku, you're visitor " (or (:value counter) 0))])))

; (defpage "/welcome-mongo" []
;   (common/mongo-init)
;   (let [counter (fetch-and-modify :firstcollection {:_id "counter"} {:$inc {:value 1} } :return-new true :upsert? true)]
;     (common/layout [:p (str "Welcome to noir-heroku, you're visitor " (or (:value counter) 0))])))

; (defpage "/welcome-mongo" [] (common/mongo-init) (common/layout [:h1 "MONGO"]))

(defpage "/welcome-mongo" []
  (with-mongo (make-connection "b1tw1se" "127.0.0.1" 27017) 
    (let [counter (fetch-and-modify :firstcollection {:_id "counter"} {:$inc {:value 1}} :return-new true :upsert? true)]
      (common/layout [:p (str "Welcome to b1tw1se.  You are visitor number " (or (:value counter) 0))]))))
