(ns clojure_ttt.minimax_injections-spec
  (:use [clojure_ttt.minimax_injections])
  (:use [speclj.core]))

(describe "Minimax Injections"
  (context "Wins"
    (it "Recognizes winning move (row 1)."
      (let [context { :player :x
                      :board '(:x :x nil
                               :o :o nil
                               nil nil nil)}]
        (should (wins? 3 context))
      )
    )
    (it "Recognizes non-winning move."
      (let [context { :player :x
                      :board '(:x :o nil
                               :o :x nil
                               nil nil nil)}]
        (should-not (wins? 3 context))
      )
    )
  )
  ; TODO: Move this out into board utils or something
  (context "Player squares"
    (it "finds player x squares"
      (should= #{1 2 8} (player-squares '(:x :x nil
                                        nil nil :o
                                        nil :x nil)
                                       :x))
    )
    (it "finds player o squares"
      (should= #{5 9} (player-squares '(:x :x nil
                                        nil :o nil
                                        nil :x :o)
                                       :o))
    )
  )
)

(run-specs)
