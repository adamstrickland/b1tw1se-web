(ns b1tw1se.models.post
  (:require [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session]
            [b1tw1se.models.account :as account])
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))
  ; (:import com.petebefin.markdown.MarkdownProcessor)

            ; [clj-time.core :as ctime]
            ; [clj-time.format :as tform]
            ; [clj-time.coerce :as coerce]

; (def date-format (tform/formatter "yyyy-MM-dd" (ctime/default-time-zone)))

(defrecord Post [_id author created_at content])

(defn init [rec] (Post. (:_id rec) (account/init (:author rec)) (:created_at rec) (:content rec)))
