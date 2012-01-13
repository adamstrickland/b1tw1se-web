(ns b1tw1se.models.thread
  (:require [simpledb.core :as db]
            [clj-time.core :as ctime]
            [clj-time.format :as tform]
            [clj-time.coerce :as coerce]
            [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session])
)

(def posts-per-page 10)

(defrecord thread [
	_id
	title
	posts])