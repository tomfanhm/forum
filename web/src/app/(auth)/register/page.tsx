import React from "react"
import { Metadata } from "next"
import Image from "next/image"
import Link from "next/link"

import { Button } from "@/components/ui/button"
import RegisterForm from "@/components/auth/register-form"

export const metadata: Metadata = {
  title: "Register",
  description: "Create a new account",
}

const Page: React.FC = () => {
  return (
    <div className="flex min-h-screen flex-1 flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <Link href="/">
          <Image
            className="mx-auto h-10 w-auto"
            src="/logo.png"
            alt="Logo"
            width={40}
            height={40}
            priority
          />
        </Link>
        <h2 className="text-foreground mt-6 text-center text-2xl leading-9 font-bold tracking-tight">
          Create an account
        </h2>
      </div>
      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
        <RegisterForm redirectUrl="/dashboard" />
      </div>
      <p className="text-muted-foreground mt-10 text-center text-sm">
        Already a member?
        <Button variant="link" className="ml-1" asChild>
          <Link href="/login">Login now</Link>
        </Button>
      </p>
    </div>
  )
}
export default Page
