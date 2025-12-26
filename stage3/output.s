.intel_syntax noprefix
.global main

.section .data
	a: .long 0
	b: .long 0

.section .text
main:
	push rbp
	mov rbp, rsp
	MOV EAX, 10
	MOV [a], EAX
	MOV EAX, 300
	MOV [b], EAX
	MOV EAX, 0
	MOV [a], EAX
	MOV EAX, [b]
	pop rbp
	ret
	mov eax, 0
	pop rbp
	ret
