(ns hello_comp.views.layout
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5 include-css include-js)]))

(defn common [title & body]
  (html5
   [:html {:ng-app ""}
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      ;;[:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
      [:title title]
  (include-css "http://fonts.googleapis.com/css?family=Sigmar+One&v1")
  (include-css "/css/style.css")
  (include-css "/css/bootstrap.min.css")
  (include-js "https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js")]
  [:body
    [:div {:id "header"}
      [:h1 {:class "container"} "PICMI"]]
    [:div {:id "content" :class "container"} body]]]))

(defn four-oh-four []
  (common "Page Not Found"
          [:div {:id "four-oh-four"}
          "The page you requested could not be found"]))
