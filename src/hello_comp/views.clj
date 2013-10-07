(ns hello_comp.views
  (:use [hiccup core page]))

(defn headerTempl [title]
 [:head
  [:title title]
  (include-css "/css/style.css")
  (include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js")])


(defn index-page []
  (html5
    [:html {:ng-app ""}
     (headerTempl "PicMi")
     [:body
      [:div
       [:label "Name :"]
       [:input {:type "text" :ng-model "yourName" :placeholder "Enter a name here"}]
       [:h1 "Hello {{yourName}} !"]]]]))
