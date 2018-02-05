import sys

inFile = sys.argv[1]
iF = open(inFile)
inComment = 0
notes = inFile + ".notes"
slides = inFile + ".slides"

notesFile = open(notes,'w')
slidesFile = open(slides,'w')

for line in iF:
	if (line.find("notesonly")!=-1) & (inComment == 0):
	# if (line[0]=='*') & (inComment == 0):
		inComment = 1
	# elif line[0]=='*':
	elif line.find("notesonly")!=-1:
		inComment = 0
	# if (inComment == 0) & (line[0]!='*'):
	if (inComment == 0) & (line.find("notesonly")==-1):
		slidesFile.write(line)
	# if (line[0]!='*'):
	if (line.find("notesonly")==-1):
		notesFile.write(line)

slidesFile.close()
