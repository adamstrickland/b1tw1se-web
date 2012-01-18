(ns b1tw1se.models.account
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.config.connection :as conn])
  (:use somnium.congomongo)
  (:use b1tw1se.lib.uuid)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))


(defrecord Account [_id first-name last-name])
(defmulti full-name class)
(defmethod full-name Account [a] (str (:first-name a) " " (:last-name a)))

(defn init [rec] (Account. (:_id rec) (:first-name rec) (:last-name rec)))

(defn create [email fname lname]
	(with-mongo conn/init
		(insert! :accounts {:email email 
							:first-name fname 
							:last-name lname})))