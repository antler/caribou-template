(ns leiningen.new.caribou
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]
            [clojure.string :as string]
            [clojure.java.io :as io]))

(def pool "abcdefghijklmnopqrstuvwxyz0123456789")

(defn rand-str
  ([n] (rand-str n pool))
  ([n pool]
     (string/join
      (map
       (fn [_]
         (rand-nth pool))
       (repeat n nil)))))

(def render (renderer "caribou"))

(defn caribou
  "Generates a new Caribou project"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :session-key (rand-str 16)}]
    (main/info (str "Generating new Caribou project called " name))
    (->files data
             [".gitignore" (render ".gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["Procfile" (render "Procfile" data)]
             ["src/{{sanitized}}/boot.clj" (render "src/skel/boot.clj" data)]
             ["src/{{sanitized}}/routes.clj" (render "src/skel/routes.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "src/skel/core.clj" data)]
             ["src/{{sanitized}}/helpers.clj" (render "src/skel/helpers.clj" data)]
             ["src/{{sanitized}}/controllers/home.clj" (render "src/skel/controllers/home.clj" data)]
             ["src/{{sanitized}}/hooks/model.clj" (render "src/skel/hooks/model.clj" data)]
             ["src/{{sanitized}}/migrations/admin.clj" (render "src/skel/migrations/admin.clj" data)]
             ["src/{{sanitized}}/migrations/default.clj" (render "src/skel/migrations/default.clj" data)]
             ["src/{{sanitized}}/migrations/order.clj" (render "src/skel/migrations/order.clj" data)]
             ["resources/config/development.clj" (render "resources/config/development.clj" data)]
             ["resources/config/production.clj" (render "resources/config/production.clj" data)]
             ["resources/config/staging.clj" (render "resources/config/staging.clj" data)]
             ["resources/config/test.clj" (render "resources/config/test.clj" data)]
             ["resources/config/heroku.clj" (render "resources/config/heroku.clj" data)]
             ["resources/templates/home.html" (render "resources/templates/home.html" (assoc data :verbed "{{verbed}}"))]
             ["resources/public/js/{{name}}.js" (render "resources/public/js/skel.js" data)]
             ["resources/public/css/{{name}}.css" (render "resources/public/css/skel.css" data)]
             ["resources/cljs/{{name}}/core.cljs" (render "resources/cljs/skel/core.cljs" data)]
             ["resources/cljs/{{name}}/connect.cljs" (render "resources/cljs/skel/connect.cljs" data)]
             ["resources/public/favicon.ico" (render "resources/public/favicon.ico")]
             ["resources/public/img/caribou-logo.png" (io/input-stream (io/resource "public/img/caribou-logo.png"))]
             ["resources/public/img/favicon.png" (io/input-stream (io/resource "public/img/favicon.png"))]
             ["resources/public/img/instrument.svg" (render "resources/public/img/instrument.svg")]
             ["resources/public/img/teepee_golden.svg" (render "resources/public/img/teepee_golden.svg")]
             ["resources/templates/errors/404.html" (render "resources/templates/errors/404.html")]
             ["resources/templates/errors/500.html" (render "resources/templates/errors/500.html")]
             ["app/.gitkeep" (render "app/.gitkeep")]
             ;; ["app/assets/.gitkeep" (render "app/assets/.gitkeep")]
)))

