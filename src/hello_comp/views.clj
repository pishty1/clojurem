(ns hello_comp.views
  (:use [hiccup core page]))

(defn headerTempl [title]
 [:head
  [:title title]
  (include-css "/css/style.css")
  (include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js")])


(defn index-page []
  (html5
    [:html {:an-app ""}
     (headerTempl "PicMi")
     [:body
        [:h1 {:id "myId"}"Hello World from template"]]]))
