(defproject {{name}} "0.12.0"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [org.immutant/immutant "1.0.0"]
                 [antler/caribou-frontend "0.12.0"]
                 [antler/caribou-admin "0.12.0"]
                 [antler/caribou-api "0.12.0"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :dev-dependencies [[lein-ring "0.8.2"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  :source-paths ["src"]
  :resource-paths ["resources/"]
  :min-lein-version "2.0.0"
  :migration-namespace {{name}}.migrations
  :main {{name}}.core
  :ring {:handler {{name}}.core/handler
         :init {{name}}.core/init
         :port 33333
         :auto-reload? false
         :servlet-name "{{name}}-frontend"}
  :immutant {:context-path "/"
             :init {{name}}.immutant/init})
