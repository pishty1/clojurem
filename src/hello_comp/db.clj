(ns  hello-comp.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]))



;; using MongoOptions allows fine-tuning connection parameters,
;; like automatic reconnection (highly recommended for production environment)
(let [^MongoOptions opts (mg/mongo-options :threads-allowed-to-block-for-connection-multiplier 300)
      ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017)]
  (mg/connect! sa opts))

(mg/set-db! (mg/get-db "pishtydb"))

;; returns a write result
(defn insertDoc []
  (mc/insert "documents" {:name "John" :age 30}))

