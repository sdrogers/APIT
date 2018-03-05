python extractor.py PatternNotes.mk
pandoc -V geometry='margin=1in' --toc -o notes.pdf PatternNotes.mk.notes
./compile_html.sh
pandoc -t BEAMER --toc -o slides.pdf PatternNotes.mk.slides