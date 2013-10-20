(ns  hello-comp.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]
			[noir.util.crypt :as crypt])			
  (:import [com.mongodb MongoOptions ServerAddress]))

;; like automatic reconnection (highly recommended for production environment)
(let [^MongoOptions opts (mg/mongo-options :threads-allowed-to-block-for-connection-multiplier 300)
      ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017)]
  (mg/connect! sa opts))

(mg/set-db! (mg/get-db "clojurem-db"))

(defn user-exist? [email]
	(mc/any? "registered_users" { :email email }))

(defn save-user [email password]
	(let [ encrypted_passwrod (crypt/encrypt password) user_exist (user-exist? email) ]
	(if (false? user_exist)
		((mc/insert "registered_users" { :email email :password encrypted_passwrod })(str email " is saved"))
		(str email "exist already!!"))))

;;return all documents
(defn returnAll []
  (mc/find-maps "documents"))
