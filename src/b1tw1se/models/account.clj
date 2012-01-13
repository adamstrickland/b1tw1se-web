(ns b1tw1se.models.account
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session])
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))


(defrecord Account [_id first-name last-name])
(defmulti full-name class)
(defmethod full-name Account [a] (str (:first-name a) " " (:last-name a)))

(defn init [rec] (Account. (:_id rec) (:first-name rec) (:last-name rec)))