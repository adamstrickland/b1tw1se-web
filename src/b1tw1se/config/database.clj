(ns b1tw1se.config.database
  (:use [noir.core :only [defpartial]]
        [hiccup.core]
        [hiccup.page-helpers])
  (:use [somnium.congomongo])
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(def db-name "b1tw1se")

(def db-host "127.0.0.1")

(def db-port 27017)

(def ^:dynamic connection (make-connection db-name :host db-host :port db-port))

; (defn split-mongo-url [url]
;   "Parses mongodb url from heroku, eg. mongodb://user:pass@host:port/db"
;   (let [matcher (re-matcher #"^.*://(.*?):(.*?)@(.*?):(\d+)/(.*)$" url)]
;     (when (.find matcher)
;       (zipmap [:match :user :pass :host :port :db] (re-groups matcher)))))

; ; (defn gather-mongo-urls
; ;   (filter #(not (nil? %)) (conj (map #(get (System/getenv) %) ["MONGOHQ_URL" "MONGOLAB_URI"]) "mongodb://localhost:27017/b1tw1se")))

; (defn find-heroku-urls []
;   (map #(get (System/getenv) %) ["MONGOHQ_URL" "MONGOLAB_URI"]))

; (defn using-heroku-url? [] (not (nil? (first (find-heroku-urls)))))

; ; (defn init-mongo
; ;   [mongo-url]
; ;   (let [config (split-mongo-url mongo-url)]
; ;     (println "Initializing MongoDB @ " mongo-url)
; ;     (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
; ;     (authenticate (:user config) (:pass config))))

; ; (defn init-mongo
; ;   [mongo-url]
; ;   (let [config (split-mongo-url mongo-url)]
; ;     (println "Initializing MongoDB @ " mongo-url)
; ;     (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
; ;     (authenticate (:user config) (:pass config))
; ;     (or (collection-exists? :firstcollection) (create-collection! :firstcollection))))

; ; (def conn 
; ;   (make-connection "mydb"
; ;                    :host "127.0.0.1"
; ;                    :port 27017))
; ; (set-connection! conn)

; (defn connect-local-db []
;   (let []
;     (println "Initializing local MongoDB")
;     ; (make-connection "b1tw1se"
;     ;                  :host "127.0.0.1"
;     ;                  :port 27017)
;     (mongo! :db "b1tw1se" :host "127.0.0.1" :port 27017)))

; (defn connect-heroku-db [mongo-url]
;   (let [config (split-mongo-url mongo-url)]
;     (println "Initializing MongoDB @ " mongo-url)
;     (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
;     (authenticate (:user config) (:pass config))))

; (defn mongo-init []
;   (when (not (connection? *mongo-config*))
;     (if (using-heroku-url?) (connect-heroku-db (first (find-heroku-urls)) (connect-local-db)))))
; ;     (init-mongo (first gather-mongo-urls))))

; ; (defn mongo-init []
; ;   (when (not (connection? *mongo-config*))
; ;     (init-mongo (first ["mongodb://localhost:27017/b1tw1se"]))))



; ; (defn maybe-init []
; ;   "Checks if connection and collection exist, otherwise initialize."
; ;   (when (not (connection? *mongo-config*))
; ;     (let [mongo-url (get (System/getenv) "MONGOHQ_URL") config (split-mongo-url mongo-url)]
; ;       (println "Initializing mongo @ " mongo-url)
; ;       (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
; ;       (authenticate (:user config) (:pass config))
; ;       (or (collection-exists? :firstcollection)
; ;         (create-collection! :firstcollection)))))