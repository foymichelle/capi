(ns capi.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]))


;; HANDLERS
(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn what-is-my-ip [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (:remote-addr request)})

;; MIDDLEWARE

(defn wrap-content-type [handler content-type]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type))))

(def app
  (wrap-content-type what-is-my-ip "text/html"))
