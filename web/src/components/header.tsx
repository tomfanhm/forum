"use client"

import React from "react"
import Image from "next/image"
import Link from "next/link"

import { siteConfig } from "@/config/site"
import { useAuthStore } from "@/stores/use-auth-store"

import ModeToggle from "./mode-toggle"
import { Button } from "./ui/button"
import UserMenu from "./user-menu"

const Header: React.FC = () => {
  const { auth } = useAuthStore()
  return (
    <header className="bg-background">
      <nav className="mx-auto flex items-center justify-between p-6 lg:px-8">
        <div className="flex lg:flex-1">
          <Link href="/" className="flex items-center gap-2">
            <Image
              className="size-8"
              src="/logo.png"
              alt="Logo"
              width={32}
              height={32}
              priority
            />
            <span className="hidden text-base font-semibold lg:block">
              {siteConfig.name}
            </span>
          </Link>
        </div>
        {/* Desktop login */}
        <div className="flex flex-1 justify-end gap-4">
          <ModeToggle />
          {auth ? (
            <UserMenu auth={auth} />
          ) : (
            <Button asChild>
              <Link href="/login">Log in</Link>
            </Button>
          )}
        </div>
      </nav>
    </header>
  )
}
export default Header
