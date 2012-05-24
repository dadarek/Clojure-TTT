(ns clojure_ttt.minimax_injections-spec
  (:use [clojure_ttt.minimax_injections])
  (:use [speclj.core]))

(describe "Minimax Injections"
  (context "Wins"
    (it "Recognizes winning move."
      (let [context { :player :x
                      :board '(:x :x nil
                               :o :o nil
                               nil nil nil)}]
        (should (wins? 3 context))
      )
    )
    (it "Recognizes non-winning move."
      (let [context { :player :x
                      :board '(:x :x nil
                               :o :o nil
                               nil nil nil)}]
        (should-not (wins? 6 context))
      )
    )
  )
)

(run-specs)
