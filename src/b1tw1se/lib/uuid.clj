(ns b1tw1se.lib.uuid
	(:import (java.util UUID)))

(defn uuid []
	(.toString (UUID/randomUUID)))