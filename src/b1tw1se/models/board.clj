(ns b1tw1se.models.board
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.models.topic :as topic])
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(defrecord Board [
	_id 
	title 
	topics])

(defn init [rec] (Board. (:_id rec) (:title rec) (map #(topic/init %) (:threads rec))))

(defn find-all []
      (with-mongo common/conn
            (let [boards (fetch :boards)]
                  (map #(init %) boards))))