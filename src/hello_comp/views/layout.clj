(ns hello_comp.views.layout
  (:use [hiccup.core :only (html)]
        [hiccup.element :only (link-to)]
        [hiccup.page :only (html5 include-css include-js)]))

(defn common [& body]
  (html5
   [:html {:ng-app ""}
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
      [:title "PICMI"]
  (include-css "/css/bootstrap.min.css")
  (include-js "/js/angular.min.js")]
  [:body {:style ""}
    [:div {:class "navbar navbar-default navbar-fixed-top"}
     [:div {:class "container"}
      [:div {:class "navbar-header"}
       (link-to {:class "navbar-brand"} "/" "PicMi")]
      [:div {:class "navbar-collapse collapse" :id "navbar-main"}
       [:ul {:class "nav navbar-nav"}
        [:li
         (link-to "/about" "About")]
        [:li
         (link-to "/account" "Account")]]
       [:ul {:class "nav navbar-nav navbar-right"}
        [:li
         (link-to "/login" "Login")]]]]]
    [:div {:id "header"}
      [:h1 {:class "container"} "PICMI H1"]]
    [:div {:id "content" :class "container"} body]]]))

(defn four-oh-four []
  (common "Page Not Found"
          [:div {:id "four-oh-four"}
          "The page you requested could not be found"]))
