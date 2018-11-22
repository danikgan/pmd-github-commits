# Script for my Research Project

## Description
This script downloads commits at a particular time of the specified projects. It runs PMD over each instance of the commit, and saves the count in a XLSX document. Current version requires the correct input, otherwise the behaviour is undefined.

## Requirements
1. Install PMD exactly as specified in https://pmd.github.io; means having the same directory for the PMD under the user folder.
2. Specify the path to which the repositories will be downloaded.
3. At that path there must exist a "links.txt" file, which will contain the links to all the repositories, which will be used in the process.
4. Specify the month and the year to which the process will backtrace.

There will be created a file named "osp-records.xlsx" upon completion, which will contain all the counts of "quality alerts" discovered.
