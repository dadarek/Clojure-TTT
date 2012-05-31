(ns clojure_ttt.game_loop-spec
  (:use [clojure_ttt.game_loop])
  (:use [clojure_ttt.runner])
  (:import [clojure_ttt.human_player HumanPlayer])
  (:use [speclj.core]))

(defn inc-counter [counter]
  (dosync (ref-set counter (inc @counter))))

(deftype MockLoopUI [play-again-counter play-again-responses
                     go-first-counter go-first-responses]
  LoopUI
  (play-again? [_]
    (let [result (nth play-again-responses @play-again-counter)]
      (inc-counter play-again-counter)
      result))

  (go-first? [_]
    (let [result (nth go-first-responses @go-first-counter)]
      (inc-counter go-first-counter)
      result)))

  (describe "Game Loop"
    (it "Plays until play-again returns false"
      (def counter (ref 0))
      (def ui (MockLoopUI. counter [true true true false]
                           (ref 0) [true true true true]))
      (with-redefs [run (fn [& args] true)]
        (do (play nil nil ui)))
      (should= 4 @counter))

    (it "Asks if player wants to go first"
      (def counter (ref 0))
      (def ui (MockLoopUI. counter [true true false]
                           (ref 0) [true true true]))
      (with-redefs [run (fn [& args] true)]
        (do (play nil nil ui)))
      (should= 3 @counter))

    (it "Lets human go first if he wants to"
      (def ref-p1 (ref nil))
      (def ref-p2 (ref nil))
      (def ui (MockLoopUI. (ref 0) [false]
                           (ref 0) [true]))

      (with-redefs [run (fn [p1 p2 & args] (dosync
                          (ref-set ref-p1 p1)
                          (ref-set ref-p2 p2)))]
        (do (play "Human" "Computer" ui)))
      (should= "Human" @ref-p1)
      (should= "Computer" @ref-p2))

    (it "Lets human go second if he wants to"
      (def ref-p1 (ref nil))
      (def ref-p2 (ref nil))
      (def ui (MockLoopUI. (ref 0) [false]
                           (ref 0) [false]))

      (with-redefs [run (fn [p1 p2 & args] (dosync
                          (ref-set ref-p1 p1)
                          (ref-set ref-p2 p2)))]
        (do (play "Human" "Computer" ui)))
      (should= "Computer" @ref-p1)
      (should= "Human" @ref-p2))

    (it "Passes the UI into the play method"
      (def ref-ui (ref nil))
      (def ui (MockLoopUI. (ref 0) [false]
                           (ref 0) [false]))
      (with-redefs [run (fn [_ _ ui]
                          (dosync (ref-set ref-ui ui)))]
        (do (play nil nil ui)))
      (should= ui @ref-ui)))

(run-specs)

