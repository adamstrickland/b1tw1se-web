(ns b1tw1se.models.board
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.models.topic :as topic]
            b1tw1se.models.account
            b1tw1se.models.post
            b1tw1se.models.topic
            [b1tw1se.config.database :as database])
  (:import b1tw1se.models.topic.Topic
         b1tw1se.models.account.Account
         b1tw1se.models.post.Post)
  (:use somnium.congomongo)
  (:use b1tw1se.lib.uuid)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(defrecord Board [_id title topics])

(defn init [rec] (Board. (:_id rec) (:title rec) (map #(topic/init %) (:topics rec))))

(defn find-all []
  (with-mongo database/connection
    (let [boards (fetch :boards)]
      (map #(init %) boards))))

(defn fake-board []
  (Board. (uuid) "Salary & Stuff" [
    (Topic. (uuid) "How much do you make?" [
      (Post. (uuid) (Account. (uuid) "Adam" "Strickland") "2012-01-01" "This year, with bonus, I'll pull in $155K (that's $140K salary + ~ $15K bonus)")])]))

(defn find-one [id]
  (with-mongo database/connection
      ; [:div (str "BOARD " (:title (with-mongo conn/init (fetch :boards :one? true :where {:_id (object-id id)}))))]
    (fetch :boards :one? true :where {:_id (object-id id)})))
  ; fake-board)

(defn find-fake []
  (cons (fake-board) (find-all)))

; (defn find-fake [] nil)
(defn create [title]
  (with-mongo database/connection
    (insert! :boards { :title title })))