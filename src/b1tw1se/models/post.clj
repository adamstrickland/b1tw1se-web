(ns b1tw1se-web.models.post
  (:require [simpledb.core :as db]
            [clj-time.core :as ctime]
            [clj-time.format :as tform]
            [clj-time.coerce :as coerce]
            [clojure.string :as string]
            [noir.validation :as val]
            [noir.session :as session])
  ; (:import com.petebefin.markdown.MarkdownProcessor)
)

(def date-format (tform/formatter "yyyy-MM-dd" (ctime/default-time-zone)))

(defrecord post [_id author created_at content])
