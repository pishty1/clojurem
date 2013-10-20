(ns hello-comp.helper
  (:refer-clojure :exclude [compare])
  (:use [hello-comp.db :as db])
  (:require [noir.util.crypt :as crypt]
            [ring.util.response :as response]))

 ;; Helper for Authentication methods/functions
