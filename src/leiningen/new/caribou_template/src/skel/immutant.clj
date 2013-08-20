(ns {{name}}.immutant
  (:require [immutant.daemons :as daemons]
            [immutant.jobs :as jobs]
            [immutant.messaging :as messaging]
            [immutant.web :as web]
            ;; [immutant.repl :as repl]
            [immutant.util :as util]
            [{{name}}.core :as core]))

(defn init
  []
  (core/init)
  (web/start "/" #'core/handler))
