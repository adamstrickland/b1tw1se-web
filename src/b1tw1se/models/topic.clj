(ns b1tw1se.models.topic
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.models.post :as post]
            [b1tw1se.config.connection :as conn])
  (:use somnium.congomongo)
  (:use b1tw1se.lib.uuid)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(def posts-per-page 10)

(defrecord Topic [_id title posts])

(defn init [rec] (Topic. (:_id rec) (:title rec) (map #(post/init %) (:posts rec))))

(defn create [title content user board_id]
	(with-mongo conn/init
		(let [board (fetch-one :boards {:_id board_id})]
			(update! :boards board (merge board {:topics (cons {:_id (uuid) :title title :posts [(post/create content user)]} (:topics board))} )) )))