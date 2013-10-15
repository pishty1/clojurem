(ns hello_comp.views.displays
  (:use [compojure.core]
        [hiccup core page element]
        [hiccup.form :only (form-to label text-field submit-button check-box radio-button password-field)])
  (:require [hello_comp.views.layout :as layoutT]))

(defn skill-label [skill level value checked?]
  (label {:class "radio inline"} (str skill "-" level)
         (conj (radio-button {:id (str skill "-" level)} skill checked?  value) level)))

;;this function now works but dont like the way the parameters are passed in
(defn render-row [skill]
  [:div {:class "control-group"}
     (label {:class "control-label"} skill skill)
     [:div  {:class "controls"}
      (map skill-label [skill    skill     skill         skill]
                       ["noobie" "alright" "pretty good" "call me Gandalf"]
                       [1        2         3             4]
                       [true     false     false         false])]])


(defn skill-radio []
  (form-to {:class "form-horizontal"}[:get "/skillform"]
   [:fieldset
    [:label "Skills Form"]
    (map render-row ["Clojure" "Scala" "Java" "JBoss"])
    [:div {:class "control-group"}
     [:div {:class "controls"}
      (submit-button {:id "singlebutton" :class "btn btn-primary"} "SKILLS")]]]))



(defn indexP [counter]
  (html5 [:div (str "hello " counter)]))

(defn login-form []
  (form-to {:class "form-signin"} [:get "/userlogin"]
   [:h2 {:class "form-signin-heading"} "In you go"]
   (text-field {:class "form-control" :placeholder "Email address" :autofocus ""} "emailaddress")
   (password-field {:class "form-control" :placeholder "Password"} "password")
   (label {:class "checkbox"} "remember-cb" (conj (check-box "remember-cb" false "remember-me") "Remember Me"))
   (submit-button {:class "btn btn-lg btn-primary btn-block"} "Sign In")
   [:span {:class "label label-info"} (link-to "/registration/new" "Create an Account !")]))

(defn registration-form []
  (form-to {:class "form-signin"} [:get "/userlogin"]
   [:h2 {:class "form-signin-heading"} "Sign Up"]
   (text-field {:class "form-control" :placeholder "Email address" :autofocus ""} "emailaddress")
   (password-field {:class "form-control" :placeholder "Password"} "password")
   (submit-button {:class "btn btn-lg btn-primary btn-block"} "Create My Account")))

(defn createlogin []
  (layoutT/common (registration-form)))

(defn loginpage [logedin?]
  (layoutT/common (login-form)))

(defn show-form-simple []
  (layoutT/common (skill-radio)))

(defn indexpage [counter]
  (layoutT/common (indexP counter)))
