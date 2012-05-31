(ns clojure_ttt.runner-spec
  (:use [clojure_ttt.runner])
  (:use [clojure_ttt.board_utilities])
  (:use [speclj.core]))

(defrecord MockPlayer [moves symbol-ref next-move-pointer]
  Player
  (next-move [this x-or-o board]
    (do
      (dosync (ref-set symbol-ref x-or-o))
      (let [result (nth moves @next-move-pointer)]
        (dosync (ref-set next-move-pointer (inc @next-move-pointer)))
       result))))

(defn increment-counter [counter] (if (not= nil counter) (dosync (ref-set counter (inc @counter)))))

(defrecord DummyUI []
  RunnerUI
  (redraw [_ _] true)
  (announce-next-turn [_ _] true)
  (announce-next-move-taken [_ _ _] true))

(defrecord CountingUI [redraw-counter next-turn-counter next-move-counter]
  RunnerUI
  (redraw [_ board] (increment-counter redraw-counter))
  (announce-next-turn [_ player] (increment-counter next-turn-counter))
  (announce-next-move-taken [_ player move] (increment-counter next-move-counter))
)

(describe "Game Runner"
  (it "Listens to players"
    (should= '(:x :o :x
               :x :o :o
               :o :x :x)
              (run (MockPlayer. '(1 3 4 8 9) (ref nil) (ref 0))
                   (MockPlayer. '(2 5 6 7) (ref nil) (ref 0))
                   (DummyUI.))))

  (it "Stops when somebody wins"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 2 3) (ref nil) (ref 0))
                   (MockPlayer. '(4 5) (ref nil) (ref 0))
                   (DummyUI.))))

  (it "Doesn't allow duplicate moves"
    (should= '(:x  :x  :x
               :o  :o  nil
               nil nil nil)
              (run (MockPlayer. '(1 3 2) (ref nil) (ref 0))
                   (MockPlayer. '(1 4 5) (ref nil) (ref 0))
                   (DummyUI.))))

  (it "Notifies UI on each turn."
    (let [redraw-counter (ref 0)
          next-turn-counter (ref 0)
          next-move-counter (ref 0)]
      (run (MockPlayer. '(1 3 8) (ref nil) (ref 0))
           (MockPlayer. '(6 4 5) (ref nil) (ref 0))
           (CountingUI. redraw-counter next-turn-counter next-move-counter nil nil))
      (should= 6 @redraw-counter)
      (should= 6 @next-turn-counter)
      (should= 6 @next-move-counter)))

  (it "Tells first player he's 'x', second player he's 'o'"
    (let [x-ref (ref nil)
          o-ref (ref nil)]
      (run (MockPlayer. '(1 2 3) x-ref (ref 0))
           (MockPlayer. '(4 5) o-ref (ref 0))
           (DummyUI.))
      (should= :x @x-ref)
      (should= :o @o-ref))))

(run-specs)
