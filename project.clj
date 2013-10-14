(defproject hello-comp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]
                 [hiccup-bootstrap "0.1.2"]
                 [com.novemberain/monger "1.5.0"]
                 [ring "1.2.0"]
                 [sandbar "0.3.3"]]
  :plugins [[lein-ring "0.8.7"]
            [codox "0.6.6"]]
  :ring {:handler hello-comp.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]
                        [criterium "0.4.2"]]}})
