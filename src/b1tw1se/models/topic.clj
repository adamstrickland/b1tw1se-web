(ns b1tw1se.models.topic
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.models.post :as post]
            [b1tw1se.config.database :as database])
  (:use somnium.congomongo)
  (:use b1tw1se.lib.uuid)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(def posts-per-page 10)

(defrecord Topic [_id title posts])

(defn init [rec] (Topic. (:_id rec) (:title rec) (map #(post/init %) (:posts rec))))

(defn create [title board_id]
	(with-mongo database/connection
		(let [b (fetch-by-id :boards (object-id board_id))]
; (merge t {:topics (cons {:title "dbag 2"} (:topics t))})
      (update! :boards b (merge b {:topics (cons {:_id (uuid) 
                                                  ; :posts [(post/create content user)]
                                                  :title title } (:topics b))})))))


			; (update! :boards b (merge b {:topics (cons {:_id (uuid) 
   ;                                                :title title 
   ;                                                ; :posts [(post/create content user)]
   ;                                               } (or (:topics b) []))} )) )))


(defn find-one [id]
  nil)