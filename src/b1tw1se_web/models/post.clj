(ns b1tw1se-web.models.post
  (:require [simpledb.core :as db]
            [clj-time.core :as ctime]
            [clj-time.format :as tform]
            [clj-time.coerce :as coerce]
            [clojure.string :as string]
            [b1tw1se-web.models.user :as users]
            [noir.validation :as val]
            [noir.session :as session])
  ; (:import com.petebefin.markdown.MarkdownProcessor)
)

(def posts-per-page 10)
(def date-format (tform/formatter "yyyy-MM-dd" (ctime/default-time-zone)))
