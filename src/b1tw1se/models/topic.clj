(ns b1tw1se.models.topic
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.models.post :as post])
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(def posts-per-page 10)

(defrecord Topic [
	_id
	title
	posts])

(defn init [rec] (Topic. (:_id rec) (:title rec) (map #(post/init %) (:posts rec))))