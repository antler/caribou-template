(ns immutant.init
  (:require [immutant.messaging :as messaging]
            [immutant.web :as web]
            [immutant.util :as util]
            [{{name}}.core :as core]))

(core/init)
;; (swank/start-server :host "127.0.0.1" :port 4010)
(web/start "/" #'core/handler)
; (web/start "/foo" a-different-ring-handler)

;; To start a Noir app:
; (server/load-views (util/app-relative "src/yellow/core/views"))
; (web/start "/" (server/gen-handler {:mode :dev :ns 'yellow}))


;; Messaging allows for starting (and stopping) destinations (queues & topics)
;; and listening for messages on a destination.

; (messaging/start "/queue/a-queue")
; (messaging/listen "/queue/a-queue" #(println "received: " %))

