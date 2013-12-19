(defproject {{name}} "0.13.3"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [http-kit "2.1.12"]
                 [org.immutant/immutant "1.0.2"]
                 [caribou/caribou-admin "0.13.3"]
                 [caribou/caribou-api "0.13.3"]
                 [schmetterling "0.0.2"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :plugins [[lein-ring "0.8.6"]
            [caribou/lein-caribou "2.13.2"]
            [lein-cljsbuild "0.3.3"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n" 
             "-Dclojure.compiler.disable-locals-clearing=true"
             "-Xmx2g" 
             "-XX:MaxPermSize=128m"
             "-XX:MaxInlineSize=0"]
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
             :init {{name}}.immutant/init}
  :cljsbuild {:repl-listen-port 44994
              :builds
              [{:source-paths ["resources/cljs"]
                :compiler {:output-to "resources/public/js/app/{{name}}.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
