(ns clojure_ttt.minimax_injections-spec
  (:use [clojure_ttt.minimax_injections])
  (:use [speclj.core]))

(describe "Minimax Injections"
  (context "Wins"
    (it "Knows when it wins"
      (let [context { :player :x
                      :board '(:x :x nil
                               :o :o nil
                               nil nil nil)}]
        (should (wins? 3 context))
      )
    )
  )
)

(run-specs)
