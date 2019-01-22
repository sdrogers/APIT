python extractor.py ThreadingNotes.md
pandoc -V geometry='margin=1in' --toc -o notes.pdf ThreadingNotes.md.notes
./compile_html.sh
pandoc -t BEAMER --toc -o slides.pdf ThreadingNotes.md.slides
