(ns b1tw1se.models.account
  (:require [simpledb.core :as db]
            [clj-time.core :as ctime]
            [clj-time.format :as tform]
            [clj-time.coerce :as coerce]
            [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session])
)



(defrecord account [_id first-name last-name])